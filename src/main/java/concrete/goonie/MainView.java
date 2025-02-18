package concrete.goonie;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MainView {
    private JPanel panel;

    private JButton btnMarketWatch;
    private JButton btnAlgo;
    private JButton btnIndicators;
    private JButton btnNewOrder;
    private JPanel tfPanel;
    private JButton btnGrid;
    private JTabbedPane tabbedPane;
    private JComboBox cbSymbols;
    private JButton button1;
    private JButton rbM15;
    private JButton rbH1;
    private JButton rbH4;
    private JButton rbD1;
    private JButton rbW1;
    private JButton BtnRemoveChart;
    private CustomButtonGroup buttonGroup;
    private DynamicTabbedPaneUI customUI;


    {
        $$$setupUI$$$();
    }

    public JTabbedPane getTabbedPane() {
        return tabbedPane;
    }

    public JComboBox getCbSymbol() {
        return cbSymbols;
    }

    public JButton getBtnGrid() {
        return btnGrid;
    }

    public JButton getBtnIndicators() {
        return btnIndicators;
    }

    public CustomButtonGroup getButtonGroup() {
        return buttonGroup;
    }

    public JButton getBtnRemoveChart() {
        return BtnRemoveChart;
    }

    public void setBackGroundCoulor(Color color) {
        customUI.setTabBarColor(color);
        panel.setBackground(color);

    }
    private void $$$setupUI$$$() {
        panel = new JPanel();
        panel.setLayout(new BorderLayout(0, 0));

        final JPanel panel1 = new JPanel();
        panel1.setLayout(new BorderLayout(0, 0));
        panel1.setBackground(new Color(0x0000001, true));
        panel.add(panel1, BorderLayout.NORTH);

        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(0x0000001, true));
        panel2.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 0));
        panel1.add(panel2, BorderLayout.CENTER);

        tfPanel = new JPanel();
        tfPanel.setBackground(new Color(0x0000001, true));
        tfPanel.setLayout(new GridBagLayout());
        panel2.add(tfPanel);

        rbM15 = new JButton();
        rbM15.setText("M15");
        GridBagConstraints gbc;

        gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        tfPanel.add(rbM15, gbc);

        rbH1 = new JButton();
        rbH1.setText("H1");

        gbc = new GridBagConstraints();
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        tfPanel.add(rbH1, gbc);

        rbH4 = new JButton();
        rbH4.setText("H4");

        gbc = new GridBagConstraints();
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        tfPanel.add(rbH4, gbc);

        rbD1 = new JButton();
        rbD1.setText("D1");

        gbc = new GridBagConstraints();
        gbc.gridx = 3;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        tfPanel.add(rbD1, gbc);

        rbW1 = new JButton();
        rbW1.setText("W1");

        gbc = new GridBagConstraints();
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        tfPanel.add(rbW1, gbc);

//        final com.intellij.uiDesigner.core.Spacer spacer1 = new com.intellij.uiDesigner.core.Spacer();
//        panel2.add(spacer1);
        button1 = new JButton();
        button1.setText("Button");
        panel2.add(button1);

        final JPanel panel3 = new JPanel();
        panel3.setLayout(new BorderLayout(0, 0));
        panel3.setBackground(new Color(0x0000001, true));
        panel1.add(panel3, BorderLayout.NORTH);

        final JPanel panel4 = new JPanel();
        panel4.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 0));
        panel3.add(panel4, BorderLayout.WEST);
        panel4.setBackground(new Color(0x0000001, true));
        cbSymbols = new JComboBox();

        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("Boom 1000 Index");
        defaultComboBoxModel1.addElement("Crash 1000 Index");
        defaultComboBoxModel1.addElement("Boom 500 Index");
        defaultComboBoxModel1.addElement("Crash 500 Index");
        defaultComboBoxModel1.addElement("XAUUSD");
        cbSymbols.setModel(defaultComboBoxModel1);
        panel4.add(cbSymbols);

        btnMarketWatch = new JButton();
        btnMarketWatch.setText("Market Watch");
        panel4.add(btnMarketWatch);

        btnAlgo = new JButton();
        btnAlgo.setText("Algo");
        panel4.add(btnAlgo);

        btnNewOrder = new JButton();
        btnNewOrder.setText("New Order");
        panel4.add(btnNewOrder);

        btnIndicators = new JButton();
        btnIndicators.setHorizontalAlignment(0);
        btnIndicators.setHorizontalTextPosition(0);
        btnIndicators.setText("Indicators");
        panel4.add(btnIndicators);

//        final com.intellij.uiDesigner.core.Spacer spacer2 = new com.intellij.uiDesigner.core.Spacer();
//        panel4.add(spacer2);
        btnGrid = new JButton();
        btnGrid.setText("Grid");
        panel4.add(btnGrid);

        final JPanel panel5 = new JPanel();
        panel5.setLayout(new GridLayout(1, 1, 0, 0));
        panel5.setBackground(new Color(0x0000001, true));
        panel3.add(panel5, BorderLayout.EAST);

        BtnRemoveChart = new JButton();
        BtnRemoveChart.setHideActionText(true);
        BtnRemoveChart.setText("X");
        panel5.add(BtnRemoveChart);

        tabbedPane = new JTabbedPane();
        tabbedPane.setPreferredSize(new Dimension(74, 24));
        tabbedPane.setTabPlacement(3);
        UIManager.put("TabbedPane.contentBorderInsets", new Insets(0, 0, 0, 0));
        UIManager.put("TabbedPane.tabAreaInsets", new Insets(0, 0, 0, 0));
        customUI = new DynamicTabbedPaneUI();
        tabbedPane.setUI(customUI);
        panel.add(tabbedPane, BorderLayout.CENTER);

        final JPanel panel7 = new JPanel();
        panel7.setLayout(new BorderLayout(0, 0));
        panel7.setBackground(new Color(0x0000001, true));
        panel.add(panel7, BorderLayout.SOUTH);

        final JPanel panel8 = new JPanel();
        panel8.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
        panel8.setBackground(new Color(-10255530));
        panel8.setPreferredSize(new Dimension(23, 28));
        panel7.add(panel8, BorderLayout.NORTH);

        final JLabel label1 = new JLabel();
        label1.setPreferredSize(new Dimension(13, 18));
        label1.setText("\uD83C\uDF9E");
        panel8.add(label1);

        buttonGroup = new CustomButtonGroup();
        buttonGroup.add(rbM15);
        buttonGroup.add(rbH1);
        buttonGroup.add(rbD1);
        buttonGroup.add(rbH4);
        buttonGroup.add(rbW1);
    }

    public JComponent $$$getRootComponent$$$() {
        return panel;
    }

    class DynamicTabbedPaneUI extends BasicTabbedPaneUI {

        private Color tabBarColor = new Color(0x2A2A2A); // Default background color

        @Override
        protected void paintTabArea(Graphics g, int tabPlacement, int selectedIndex) {
            // Custom tab background for the whole tab bar
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(tabBarColor); // Dark background for tab bar
            g2.fillRect(0, 0, tabPane.getWidth(), tabPane.getHeight());

            // Call default tab painting
            super.paintTabArea(g, tabPlacement, selectedIndex);
        }

        @Override
        protected void paintTab(Graphics g, int tabPlacement, Rectangle[] rects, int tabIndex, Rectangle iconRect, Rectangle textRect) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // Define tab shape and color
            Rectangle rect = rects[tabIndex];
            boolean isSelected = tabIndex == tabPane.getSelectedIndex();

            if (isSelected) {
                g2.setColor(new Color(0x56789A)); // Active tab color
            } else {
                g2.setColor(new Color(0x444444)); // Inactive tab color
            }

            // Draw tab rectangle
            g2.fillRoundRect(rect.x, rect.y, rect.width, rect.height, 10, 10);

            // Draw text
            g2.setColor(isSelected ? Color.WHITE : Color.LIGHT_GRAY);
            FontMetrics fm = g2.getFontMetrics();
            int textX = rect.x + (rect.width - fm.stringWidth(tabPane.getTitleAt(tabIndex))) / 2;
            int textY = rect.y + (rect.height + fm.getAscent()) / 2 - 2;
            g2.drawString(tabPane.getTitleAt(tabIndex), textX, textY);
        }

        @Override
        protected void paintContentBorder(Graphics g, int tabPlacement, int selectedIndex) {
            // Remove border completely
        }

        public void setTabBarColor(Color color) {
            this.tabBarColor = color;
            tabPane.repaint(); // Trigger UI update
        }
    }

    class CustomButtonGroup {

        private final ArrayList<JButton> buttons = new ArrayList<>();
        private JButton selectedButton = null;
        private ActionListener selectionListener;

        public void add(JButton button) {
            buttons.add(button);

            button.addActionListener(e -> selectButton(button));
        }

        public void selectButton(int index) {
            if (index >= 0 && index < buttons.size()) {
                selectButton(buttons.get(index));
            }
        }

        private void selectButton(JButton button) {
            // Deselect previous button
            if (selectedButton != null) {
                selectedButton.setEnabled(true); // Visually deselect
            }

            // Select new button
            selectedButton = button;
            selectedButton.setEnabled(false); // Visually select (disabled state)

            // Notify listener
            if (selectionListener != null) {
                selectionListener.actionPerformed(new ActionEvent(selectedButton, ActionEvent.ACTION_PERFORMED, selectedButton.getText()));
            }
        }

        public JButton getSelectedButton() {
            return selectedButton;
        }

        public void setSelectionListener(ActionListener listener) {
            this.selectionListener = listener;
        }

    }
}
