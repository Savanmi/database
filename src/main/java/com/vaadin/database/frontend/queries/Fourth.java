package com.vaadin.database.frontend.queries;

import com.vaadin.database.data.service.Phone_numbersService;
import com.vaadin.database.data.service.Telephone_exchangesService;
import com.vaadin.database.frontend.MainView;
import com.vaadin.database.frontend.QueryView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;
import java.util.List;

@Route(value = "query_4", layout = QueryView.class)
@PageTitle("Query №4")


public class Fourth extends VerticalLayout {

    private Telephone_exchangesService telephone_exchangesService;
    private final Grid<Object[]> grid;


    public Fourth(Telephone_exchangesService telephone_exchangesService) {
        this.telephone_exchangesService = telephone_exchangesService;
        this.grid = new Grid<>();

       // configureGrid();
        add(new H3("АТС с должниками"),getToolBar(), grid);
        setSizeFull();
    }

//    private void configureGrid() {
//        grid.addColumn(objects -> objects[0]).setHeader("Id атс");
//        grid.addColumn(objects -> objects[1]).setHeader("Количество должников");
//
//        grid.getColumns().forEach(col -> col.setAutoWidth(true));
//        setSizeFull();
//
//        grid.setItems(Collections.emptyList());
//    }




    private HorizontalLayout getToolBar() {

        Button exec1 = new Button("найти макс задолженность", click -> findTelephoneExchangesByDebts());
        Button exec2 = new Button("найти количество должников", click -> findTelephoneExchangesByDebtors());

        HorizontalLayout toolbar = new HorizontalLayout(exec1, exec2);
        return  toolbar;
    }

    private void findTelephoneExchangesByDebtors() {
        List<Object[]> data = null;
        grid.removeAllColumns();
        grid.addColumn(objects -> objects[0]).setHeader("Id атс");
        grid.addColumn(objects -> objects[1]).setHeader("Количество должников");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        setSizeFull();

        data = telephone_exchangesService.findTelephoneExchangesByDebtors();
        if (data.isEmpty()) {
            grid.setItems(Collections.emptyList());
        } else grid.setItems(data);

    }

    private void findTelephoneExchangesByDebts() {
        List<Object[]> data = null;
        grid.removeAllColumns();
        grid.addColumn(objects -> objects[0]).setHeader("Id атс");
        grid.addColumn(objects -> objects[1]).setHeader("Сумма задолженности");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        setSizeFull();

            data = telephone_exchangesService.findTelephoneExchangesByDebts();
            if (data.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(data);

    }

}



