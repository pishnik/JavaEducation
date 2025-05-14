package ru.java.karanin.temperature_converter.view;

import ru.java.karanin.temperature_converter.model.TemperatureScale;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class TemperatureDesktopView implements TemperatureView {
    private JFrame frame;

    private JTextField temperature;

    private JTextField convertedTemperature;

    private JComboBox<TemperatureScale> scalesFrom;

    private JComboBox<TemperatureScale> scalesTo;

    private JButton convertButton;

    public TemperatureDesktopView() {
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Конвертер температуры");
            frame.setSize(400, 200);
            frame.setResizable(false);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            frame.setLayout(new BorderLayout(5, 5));

            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridLayout(4, 2, 5, 5));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            mainPanel.add(new JLabel("Исходное значение"));
            mainPanel.add(new JLabel("Преобразованное значение"));

            temperature = new JTextField();
            mainPanel.add(temperature);

            convertedTemperature = new JTextField();
            convertedTemperature.setEditable(false);
            mainPanel.add(convertedTemperature);

            mainPanel.add(new JLabel("Из шкалы"));
            mainPanel.add(new JLabel("В шкалу"));

            scalesFrom = new JComboBox<>();
            scalesTo = new JComboBox<>();

            for (TemperatureScale scale : TemperatureScale.values()) {
                scalesFrom.addItem(scale);
                scalesTo.addItem(scale);
            }

            mainPanel.add(scalesFrom);
            mainPanel.add(scalesTo);
            scalesTo.setSelectedIndex(0);
            scalesTo.setSelectedIndex(1);

            frame.add(mainPanel, BorderLayout.NORTH);

            JPanel buttonPanel = new JPanel(new GridBagLayout());
            buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

            convertButton = new JButton("Конвертировать");
            convertButton.setEnabled(false);
            buttonPanel.add(convertButton);
            frame.add(buttonPanel, BorderLayout.SOUTH);
            frame.setVisible(true);
        });
    }

    @Override
    public void showResults(double resultTemperature) {
        convertedTemperature.setText(String.format("%.2f", resultTemperature));
    }

    @Override
    public void showError(String errorMessage) {
        JOptionPane.showMessageDialog(frame, errorMessage, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public double getTemperature() {
        return Double.parseDouble(temperature.getText());
    }

    @Override
    public TemperatureScale getScaleFrom() {
        return (TemperatureScale) scalesFrom.getSelectedItem();
    }

    @Override
    public TemperatureScale getScaleTo() {
        return (TemperatureScale) scalesTo.getSelectedItem();
    }

    @Override
    public void checkTemperatureValue() {
        convertButton.setEnabled(!temperature.getText().isEmpty());
        convertedTemperature.setText("");
    }

    @Override
    public void setPresenterCallbacks(Runnable onConvertClicked, Runnable onTemperatureChange) {
        SwingUtilities.invokeLater(() -> {
            if (onConvertClicked != null) {
                convertButton.addActionListener(e -> onConvertClicked.run());
            }

            if (onTemperatureChange != null) {
                temperature.getDocument().addDocumentListener(new DocumentListener() {
                    @Override
                    public void insertUpdate(DocumentEvent e) {
                        checkTemperatureValue();
                    }

                    @Override
                    public void removeUpdate(DocumentEvent e) {
                        checkTemperatureValue();
                    }

                    @Override
                    public void changedUpdate(DocumentEvent e) {
                        checkTemperatureValue();
                    }
                });
            }
        });
    }
}