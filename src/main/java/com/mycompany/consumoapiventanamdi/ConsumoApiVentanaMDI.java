package com.mycompany.consumoapiventanamdi;

import com.mycompany.consumoapiventanamdi.mdi.VentanaPrincipal;

public class ConsumoApiVentanaMDI {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(() -> {
            new VentanaPrincipal().setVisible(true);
        });
    }
}
