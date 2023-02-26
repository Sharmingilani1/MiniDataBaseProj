module com.notesandtaggs.minidatabaseproj {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;

    opens com.notesandtaggs.minidatabaseproj to javafx.fxml,org.hibernate.orm.core;
    exports com.notesandtaggs.minidatabaseproj;
}