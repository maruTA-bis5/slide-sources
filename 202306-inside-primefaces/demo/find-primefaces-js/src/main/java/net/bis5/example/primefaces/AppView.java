package net.bis5.example.primefaces;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named
@ViewScoped
public class AppView implements Serializable {

    public static record Row(String uuid, String name) implements Serializable {
        private Row(String name) {
            this(UUID.randomUUID().toString(), name);
        }

        // EL式からアクセスできるようにgetterを定義
        public String getUuid() {
            return uuid();
        }
        public String getName() {
            return name();
        }
    }

    private final List<Row> rows = new ArrayList<>();

    @PostConstruct
    void initialize() {
        rows.add(new Row("Jakarta Faces"));
        rows.add(new Row("PrimeFaces"));
        rows.add(new Row("OmniFaces"));
        rows.add(new Row("Mojarra"));
        rows.add(new Row("MyFaces"));
    }

    public List<Row> getRows() {
        return rows;
    }

}
