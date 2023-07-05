module main.sokobangame {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.compiler;
    requires java.desktop;
    requires java.instrument;
    requires java.logging;
    requires java.management;
    requires java.naming;
    requires java.persistence;
    requires java.sql;
    requires java.xml;
    requires javafx.base;
    requires javafx.graphics;
    requires jdk.unsupported;
    requires jdk.xml.dom;
    requires org.eclipse.persistence.asm;
    requires org.eclipse.persistence.core;
    requires org.eclipse.persistence.jpa;
    requires org.eclipse.persistence.jpa.jpql;
    requires org.eclipse.persistence.moxy;
    
    requires javafx.media;
    
    opens main.sokobangame.modelDto to javafx.base;

    requires java.xml.bind;
    requires java.base;

    opens main.sokobangame to javafx.fxml;
    exports main.sokobangame;
}
