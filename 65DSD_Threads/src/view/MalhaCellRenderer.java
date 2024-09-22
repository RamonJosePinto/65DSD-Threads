package view;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class MalhaCellRenderer extends DefaultTableCellRenderer {
    private final Color[] colors = {
            Color.WHITE, // 0 - Nada
            new Color(144, 238, 144), // 1 - Estrada Cima - Verde Claro
            new Color(135, 206, 250), // 2 - Estrada Direita - Azul Claro
            new Color(255, 255, 102), // 3 - Estrada Baixo - Amarelo
            new Color(255, 165, 0), // 4 - Estrada Esquerda - Laranja
            new Color(0, 255, 127), // 5 - Cruzamento Cima
            new Color(173, 216, 230), // 6 - Cruzamento Direita
            new Color(255, 255, 153), // 7 - Cruzamento Baixo
            new Color(255, 140, 0), // 8 - Cruzamento Esquerda
            new Color(169, 169, 169), // 9 - Cruzamento Cima e Direita
            new Color(211, 211, 211), // 10 - Cruzamento Cima e Esquerda
            new Color(192, 192, 192), // 11 - Cruzamento Direita e Baixo
            new Color(220, 220, 220)  // 12 - Cruzamento Baixo e Esquerda
    };

    private final ImageIcon[] icons;

    private ImageIcon resizeIcon(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    public MalhaCellRenderer() {
        // Carregar as imagens dos ícones
        icons = new ImageIcon[13];
        icons[1] = resizeIcon(new ImageIcon(getClass().getClassLoader().getResource("images/up-arrow.png")), 25, 25);
        icons[2] = resizeIcon(new ImageIcon(getClass().getClassLoader().getResource("images/right-arrow.png")), 25, 25);
        icons[3] = resizeIcon(new ImageIcon(getClass().getClassLoader().getResource("images/down-arrow.png")), 25, 25);
        icons[4] = resizeIcon(new ImageIcon(getClass().getClassLoader().getResource("images/left-arrow.png")), 25, 25);
        // Continue para os outros ícones...
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus, int row, int column) {
        Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

        if (value instanceof Integer) {
            int cellValue = (Integer) value;

            // Verificar se o valor corresponde a uma interseção (valores de 5 a 12, no seu caso)
            if (cellValue >= 5 && cellValue <= 12) {
                c.setBackground(Color.decode("#a6a6a6")); // Cor para interseções
            } else if (cellValue >= 0 && cellValue < colors.length) {
                // Definir a cor de fundo conforme o valor da célula
                c.setBackground(colors[cellValue]);
            } else {
                c.setBackground(Color.LIGHT_GRAY); // Cor padrão para valores inesperados
            }

            // Definir o ícone conforme o valor da célula
            if (cellValue >= 0 && cellValue < icons.length && icons[cellValue] != null) {
                setIcon(icons[cellValue]);
                setHorizontalAlignment(SwingConstants.CENTER); // Centralizar o ícone
                setText(""); // Limpar o texto, se houver, para mostrar apenas a imagem
            } else {
                setIcon(null); // Sem imagem
                setText(""); // Sem texto
            }
        }

        return c;
    }

}
