package com.vaadin.database.frontend.queries;

import com.vaadin.database.data.service.CallerService;
import com.vaadin.database.data.service.Public_phonesService;
import com.vaadin.database.frontend.MainView;
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

@Route(value = "query_1", layout = MainView.class)
@PageTitle("Query №1")
public class First extends VerticalLayout{

     private CallerService callerService;
        private final Grid<Object[]> grid;


        public First(CallerService callerService) {
            this.callerService = callerService;
            this.grid = new Grid<>();

            getToolBar().setAlignItems(Alignment.START);
            configureGrid();
            add(new H3("Получить перечень абонентов"),getToolBar(), grid);
            setSizeFull();
        }

        private void configureGrid() {
            grid.addColumn(objects -> objects[0]).setHeader("Id АТС");
            grid.addColumn(objects -> objects[1]).setHeader("Фамилия");
            grid.addColumn(objects -> objects[2]).setHeader("Имя");
            grid.addColumn(objects -> objects[3]).setHeader("Отчество");
            grid.addColumn(objects -> objects[4]).setHeader("Льготник");
            grid.addColumn(objects -> objects[5]).setHeader("Возраст");

            grid.getColumns().forEach(col -> col.setAutoWidth(true));
            setSizeFull();

            grid.setItems(Collections.emptyList());
        }

        private HorizontalLayout getToolBar() {
            IntegerField tex = new IntegerField("id атс");
            Checkbox deadhead = new Checkbox("Льготник");
            IntegerField low = new IntegerField("Возраст от");
            IntegerField up = new IntegerField("Возраст до");

            TextField second = new TextField("фамилия");


            Button exec = new Button("найти", click -> findPublicPhonesList( tex.getValue(), deadhead.getValue(), low.getValue(), up.getValue(), second.getValue()));
            HorizontalLayout toolbar = new HorizontalLayout(tex, deadhead, low, up, second, exec);
            return  toolbar;
        }

        private void findPublicPhonesList(Integer param1, boolean param2, Integer param3, Integer param4, String param5) {
            List<Object[]> shares = null;

            if(param1 != null && param3 != 0 && param4 != 0 && param5 != null){
                shares = callerService.findCallersList(param1, param2, param3, param4, param5);
                if (shares.isEmpty()) {
                    grid.setItems(Collections.emptyList());
                } else grid.setItems(shares);
            }
            else {
                grid.setItems(Collections.emptyList());
            }
        }


    }

