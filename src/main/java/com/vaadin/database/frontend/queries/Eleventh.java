package com.vaadin.database.frontend.queries;

import com.vaadin.database.data.service.PhonesService;
import com.vaadin.database.frontend.QueryView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;
import java.util.List;

@Route(value = "query_11", layout = QueryView.class)
@PageTitle("Query №11")
public class Eleventh extends VerticalLayout {
    private PhonesService phonesService;
    private final Grid<Object[]> grid;


    public Eleventh(PhonesService phonesService ) {
        this.phonesService = phonesService;
        this.grid = new Grid<>();

        configureGrid();
        add(new H3("Получить перечень спаренных телефонов, для которых есть техническая возможность заменить их на обычные"), getToolBar(), grid);
        setSizeFull();
    }

    private void configureGrid() {
        grid.addColumn(objects -> objects[0]).setHeader("Id номера телефона");
        grid.addColumn(objects -> objects[1]).setHeader("Номер телефона");
        grid.addColumn(objects -> objects[2]).setHeader("Id абонента");
        grid.addColumn(objects -> objects[3]).setHeader("Id атс");
        grid.addColumn(objects -> objects[4]).setHeader("Id адреса");


        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        setSizeFull();

        grid.setItems(Collections.emptyList());
    }

    private HorizontalLayout getToolBar() {

        Button exec = new Button("найти", click -> findDeadheadPercentage());
        HorizontalLayout toolbar = new HorizontalLayout(exec);
        return toolbar;
    }

    private void findDeadheadPercentage() {
        List<Object[]> data = null;

            data = phonesService.findPairedPhones();
            if (data.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(data);
        }
    }

