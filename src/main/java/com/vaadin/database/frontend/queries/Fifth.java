package com.vaadin.database.frontend.queries;

import com.vaadin.database.data.service.Public_phonesService;
import com.vaadin.database.frontend.MainView;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.IntegerField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import java.util.Collections;
import java.util.List;

@Route(value = "query_5", layout = MainView.class)
@PageTitle("Query №5")
public class Fifth extends VerticalLayout {
    private Public_phonesService public_phonesService;
    private final Grid<Object[]> grid;


    public Fifth(Public_phonesService public_phonesService) {
        this.public_phonesService = public_phonesService;
        this.grid = new Grid<>();

        configureGrid();
        add(getToolBar(), grid);
        setSizeFull();
    }

    private void configureGrid() {
        grid.addColumn(objects -> objects[0]).setHeader("Id телефона");
        grid.addColumn(objects -> objects[1]).setHeader("Id АТС");
        grid.addColumn(objects -> objects[2]).setHeader("Город");
        grid.addColumn(objects -> objects[3]).setHeader("Район");
        grid.addColumn(objects -> objects[4]).setHeader("Улица");
        grid.addColumn(objects -> objects[5]).setHeader("Дом");

        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        setSizeFull();

        grid.setItems(Collections.emptyList());
    }

    private HorizontalLayout getToolBar() {
        TextField regoin = new TextField("район");
        IntegerField tex = new IntegerField("id атс");

        Button exec = new Button("найти", click -> findPublicPhonesList(regoin.getValue(), tex.getValue()));
        HorizontalLayout toolbar = new HorizontalLayout(regoin,tex, exec);
        return  toolbar;
    }

    private void findPublicPhonesList(String param1, Integer param2) {
        List<Object[]> shares = null;

        if(param1 != null && param2 != null){
            shares = public_phonesService.findPublicPhonesList(param1, param2);
            if (shares.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(shares);
        }
        else {
            grid.setItems(Collections.emptyList());
        }
    }


    }