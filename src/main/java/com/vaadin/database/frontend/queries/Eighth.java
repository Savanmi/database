package com.vaadin.database.frontend.queries;

import com.vaadin.database.data.service.CallerService;
import com.vaadin.database.data.service.PhonesService;
import com.vaadin.database.frontend.MainView;
import com.vaadin.database.frontend.QueryView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.checkbox.Checkbox;
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

@Route(value = "query_8", layout = QueryView.class)
@PageTitle("Query №8")
public class Eighth extends VerticalLayout {
    private PhonesService phonesService;
    private final Grid<Object[]> grid;


    public Eighth(PhonesService phonesService) {
        this.phonesService = phonesService;
        this.grid = new Grid<>();

        configureGrid();
        add(new H3("Получить перечень телефонов"), getToolBar(), grid);
        setSizeFull();
    }

    private void configureGrid() {
        grid.addColumn(objects -> objects[0]).setHeader("Id телефона");
        grid.addColumn(objects -> objects[1]).setHeader("Id адреса");
        grid.addColumn(objects -> objects[2]).setHeader("Город");
        grid.addColumn(objects -> objects[3]).setHeader("Район");
        grid.addColumn(objects -> objects[4]).setHeader("Улица");
        grid.addColumn(objects -> objects[5]).setHeader("№ дома");
        grid.addColumn(objects -> objects[6]).setHeader("Блокирован");


        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        setSizeFull();

        grid.setItems(Collections.emptyList());
    }

    private HorizontalLayout getToolBar() {
        TextField street = new TextField();
        street.setPlaceholder("улица");
//        IntegerField tex = new IntegerField();
//        tex.setPlaceholder("# квартиры");

        Checkbox blocked = new Checkbox("Блокирован");

        Button exec = new Button("найти", click -> findPublicPhonesList( street.getValue(), blocked.getValue()));
        HorizontalLayout toolbar = new HorizontalLayout(street, blocked, exec);
        return  toolbar;
    }

    private void findPublicPhonesList(String  param1, boolean param2) {
        List<Object[]> data = null;

        if(param1 != null){
            data = phonesService.findPhonesList(param1, param2);
            if (data.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(data);
        }
        else {
            grid.setItems(Collections.emptyList());
        }
    }


}