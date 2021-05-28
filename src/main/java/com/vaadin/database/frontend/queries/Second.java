package com.vaadin.database.frontend.queries;

import com.vaadin.database.data.service.CallerService;
import com.vaadin.database.data.service.Phone_numbersService;
import com.vaadin.database.data.service.Public_phonesService;
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

@Route(value = "query_2", layout = QueryView.class)
@PageTitle("Query №2")


    public class Second extends VerticalLayout {

        private Phone_numbersService phone_numbersService;
        private final Grid<Object[]> grid;


        public Second(Phone_numbersService phone_numbersService) {
            this.phone_numbersService = phone_numbersService;
            this.grid = new Grid<>();

            configureGrid();
            add(new H3("Получить перечень свободных телефонных номеров"),getToolBar(), grid);
            setSizeFull();
        }

        private void configureGrid() {
            grid.addColumn(objects -> objects[0]).setHeader("Id номера");
            grid.addColumn(objects -> objects[1]).setHeader("Номер телефона");
            grid.addColumn(objects -> objects[2]).setHeader("Id атс");
            grid.addColumn(objects -> objects[3]).setHeader("Id адреса");

            grid.getColumns().forEach(col -> col.setAutoWidth(true));
            setSizeFull();

            grid.setItems(Collections.emptyList());
        }

        private HorizontalLayout getToolBar() {
            IntegerField tex = new IntegerField();
            tex.setPlaceholder("id атс");
            IntegerField address = new IntegerField();
            address.setPlaceholder("id адреса");


            Button exec = new Button("найти", click -> findPublicPhonesList( tex.getValue(),address.getValue()));

            HorizontalLayout toolbar = new HorizontalLayout(tex, address, exec);
            return  toolbar;
        }

        private void findPublicPhonesList(Integer param1, Integer param2) {
            List<Object[]> data = null;

            if(param1 != null && param2 != 0 ){
                data = phone_numbersService.findFreePhonesList(param1, param2);
                if (data.isEmpty()) {
                    grid.setItems(Collections.emptyList());
                } else grid.setItems(data);
            }
            else {
                grid.setItems(Collections.emptyList());
            }
        }


    }
