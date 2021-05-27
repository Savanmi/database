package com.vaadin.database.frontend.queries;

import com.vaadin.database.data.service.ClientsService;
import com.vaadin.database.data.service.Phone_numbersService;
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

@Route(value = "query_10", layout = MainView.class)
@PageTitle("Query №10")
public class Tenth extends VerticalLayout {
    private Phone_numbersService phone_numbersService;
    private final Grid<Object[]> grid;


    public Tenth(Phone_numbersService phone_numbersService) {
        this.phone_numbersService = phone_numbersService;
        this.grid = new Grid<>();

        configureGrid();
        add(new H3("Получить полную информацию об абонентах с заданным телефонным номером"), getToolBar(), grid);
        setSizeFull();
    }

    private void configureGrid() {
        grid.addColumn(objects -> objects[0]).setHeader("Id номера телефона");
        grid.addColumn(objects -> objects[1]).setHeader("Тип");
        grid.addColumn(objects -> objects[2]).setHeader("Фамилия");
        grid.addColumn(objects -> objects[3]).setHeader("Имя");
        grid.addColumn(objects -> objects[4]).setHeader("Отчество");
        grid.addColumn(objects -> objects[5]).setHeader("Возраст");


        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        setSizeFull();

        grid.setItems(Collections.emptyList());
    }

    private HorizontalLayout getToolBar() {
        IntegerField tex = new IntegerField();
        tex.setPlaceholder("Id номера");
        Button exec = new Button("найти", click -> findDeadheadPercentage( tex.getValue()));
        HorizontalLayout toolbar = new HorizontalLayout(tex, exec);
        return toolbar;
    }

    private void findDeadheadPercentage( Integer param1) {
        List<Object[]> data = null;
        if(param1 != null){
            data = phone_numbersService.findCallersInfo(param1);
            if (data.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(data);
        }  else {
            grid.setItems(Collections.emptyList());
        }

    }

}