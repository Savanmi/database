package com.vaadin.database.frontend.queries;

import com.vaadin.database.data.service.Long_distance_callsService;
import com.vaadin.database.frontend.MainView;
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

@Route(value = "query_9", layout = QueryView.class)
@PageTitle("Query №9")
public class Ninth extends VerticalLayout {
    private Long_distance_callsService long_distance_callsService;
    private final Grid<Object[]> grid;


    public Ninth(Long_distance_callsService long_distance_callsService) {
        this.long_distance_callsService = long_distance_callsService;
        this.grid = new Grid<>();

        configureGrid();
        add(new H3("Получить город с максимальным числом разговоров"), getToolBar(), grid);
        setSizeFull();
    }

    private void configureGrid() {
        grid.addColumn(objects -> objects[0]).setHeader("Город");
        grid.addColumn(objects -> objects[1]).setHeader("Число звонков");


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
            data = long_distance_callsService.findTownWithMaxCalls();
            if (data.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(data);
        }

    }

