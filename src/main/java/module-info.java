module com.pbo.simak {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires java.sql;

    opens com.pbo.simak to javafx.fxml;
    exports com.pbo.simak;
    exports com.pbo.simak.controller;
    opens com.pbo.simak.controller to javafx.fxml;
    exports com.pbo.simak.middleware;
    opens com.pbo.simak.middleware to javafx.fxml;
    exports com.pbo.simak.utils;
    opens com.pbo.simak.utils to javafx.fxml;
    exports com.pbo.simak.model;
    opens com.pbo.simak.model to javafx.fxml;
}