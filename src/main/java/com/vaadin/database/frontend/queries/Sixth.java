package com.vaadin.database.frontend.queries;

import com.vaadin.database.data.service.ClientsService;
import com.vaadin.database.data.service.Public_phonesService;
import com.vaadin.database.frontend.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;
import java.util.List;

@Route(value = "query_6", layout = MainView.class)
@PageTitle("Query №6")
public class Sixth extends VerticalLayout {
    private ClientsService clientsService;
    private final Grid<Object[]> grid;


    public Sixth(ClientsService clientsService) {
        this.clientsService = clientsService;
        this.grid = new Grid<>();

        configureGrid();
        add(new H3("Получить процентное соотношение обычных и льготных абонентов"), getToolBar(), grid);
        setSizeFull();
    }

    private void configureGrid() {
        grid.addColumn(objects -> objects[0]).setHeader("% льготников");
        grid.addColumn(objects -> objects[1]).setHeader("% нельготников");


        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        setSizeFull();

        grid.setItems(Collections.emptyList());
    }

    private HorizontalLayout getToolBar() {
        TextField region = new TextField("Район");
        IntegerField tex = new IntegerField("Id атс");
        Button exec = new Button("найти", click -> findDeadheadPercentage( tex.getValue(),region.getValue()));
        HorizontalLayout toolbar = new HorizontalLayout(region, tex, exec);
        return toolbar;
    }

    private void findDeadheadPercentage( Integer param1, String param2) {
        List<Object[]> data = null;
        if(param1 != null && param2 != null){
            data = clientsService.findDeadheadPercentage(param1,param2);
            if (data.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(data);
    }  else {
            grid.setItems(Collections.emptyList());
        }

}

}