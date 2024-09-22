package model;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;

public class MalhaTableModel extends AbstractTableModel {
    private int[][] malha;
    private int rows;
    private int cols;
    private String[] columnNames;
    private ImageIcon[] directionIcons;

    public MalhaTableModel(String filePath) throws IOException {
        loadMalhaFromFile(filePath);
        columnNames = new String[cols];
        for (int i = 0; i < cols; i++) {
            columnNames[i] = String.valueOf(i);
        }

        // Load direction icons (adjust paths as needed)
        directionIcons = new ImageIcon[13];
        directionIcons[0] = null; // No image for empty cells
        directionIcons[1] = new ImageIcon("src/images/up-arrow.png");
        directionIcons[2] = new ImageIcon("src/images/right-arrow.png");
        directionIcons[3] = new ImageIcon("src/images/down-arrow.png");
        directionIcons[4] = new ImageIcon("src/images/left-arrow.png");
        // Add more icons as needed for intersections...
    }

    private void loadMalhaFromFile(String filePath) throws IOException {
        List<int[]> tempMalha = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        this.rows = Integer.parseInt(reader.readLine());
        this.cols = Integer.parseInt(reader.readLine());

        for (int i = 0; i < rows; i++) {
            String[] line = reader.readLine().split("\\s+");
            int[] row = new int[cols];
            for (int j = 0; j < cols; j++) {
                row[j] = Integer.parseInt(line[j]);
            }
            tempMalha.add(row);
        }

        malha = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            malha[i] = tempMalha.get(i);
        }

        reader.close();
    }

    @Override
    public int getRowCount() {
        return rows;
    }

    @Override
    public int getColumnCount() {
        return cols;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return malha[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public static void createAndShowTable(String filePath) throws IOException {
        MalhaTableModel model = new MalhaTableModel(filePath);
        JTable table = new JTable(model);
        table.setTableHeader(null);
        // Defina o tamanho máximo da malha
        int maxWidth = 650;
        int maxHeight = 650;

        // Calcule a largura e altura das células com base no número de linhas e colunas
        int cellWidth = maxWidth / model.getColumnCount();
        int cellHeight = maxHeight / model.getRowCount();

        // Garanta que as células tenham o mesmo tamanho
        int cellSize = Math.min(cellWidth, cellHeight);

        // Defina a altura e largura das células
        table.setRowHeight(cellSize);
        for (int i = 0; i < table.getColumnModel().getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setPreferredWidth(cellSize);
        }

        // Centralizar horizontalmente usando um painel com layout
        JPanel panel = new JPanel(new BorderLayout());
        JScrollPane scrollPane = new JScrollPane(table);

        // Limitar o tamanho do JScrollPane para 650x650 no máximo
        scrollPane.setPreferredSize(new Dimension(maxWidth, maxHeight));

        // Adiciona a tabela ao centro do painel
        panel.add(scrollPane, BorderLayout.CENTER);

        JFrame frame = new JFrame("Malha Viária");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Centralizar a tabela no centro horizontalmente
        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER); // Panel com a tabela vai no centro do layout

        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela
    }


}
