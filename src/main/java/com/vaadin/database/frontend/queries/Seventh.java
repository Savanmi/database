package com.vaadin.database.frontend.queries;

import com.vaadin.database.data.service.CallerService;
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

@Route(value = "query_7", layout = QueryView.class)
@PageTitle("Query №7")
public class Seventh extends VerticalLayout {
    private CallerService callerService;
    private final Grid<Object[]> grid;


    public Seventh(CallerService callerService) {
        this.callerService = callerService;
        this.grid = new Grid<>();

        configureGrid();
        add(new H3("Получить перечень абонентов, имеющих параллельные телефоны"), getToolBar(), grid);
        setSizeFull();
    }

    private void configureGrid() {
        grid.addColumn(objects -> objects[0]).setHeader("Id абонента");
        grid.addColumn(objects -> objects[1]).setHeader("Фамилия");
        grid.addColumn(objects -> objects[2]).setHeader("Имя");
        grid.addColumn(objects -> objects[3]).setHeader("Отчество");
        grid.addColumn(objects -> objects[4]).setHeader("Id атс");
        grid.addColumn(objects -> objects[5]).setHeader("Тип АТС");
        grid.addColumn(objects -> objects[6]).setHeader("Льготник");
        grid.addColumn(objects -> objects[7]).setHeader("Район");


        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        setSizeFull();

        grid.setItems(Collections.emptyList());
    }

    private HorizontalLayout getToolBar() {
        TextField regoin = new TextField();
        regoin.setPlaceholder("район");
        IntegerField tex = new IntegerField();
        tex.setPlaceholder("id атс");

        Checkbox dead = new Checkbox("Есть льгота");

        Button exec = new Button("найти", click -> findPublicPhonesList( tex.getValue(), dead.getValue(), regoin.getValue()));
        HorizontalLayout toolbar = new HorizontalLayout(regoin,tex, exec);
        return  toolbar;
    }

    private void findPublicPhonesList( Integer param1, boolean param2, String param3) {
        List<Object[]> data = null;

        if(param1 != null && param3 != null){
            data = callerService.findCallerswithParallelPhones(param1, param2,param3);
            if (data.isEmpty()) {
                grid.setItems(Collections.emptyList());
            } else grid.setItems(data);
        }
        else {
            grid.setItems(Collections.emptyList());
        }
    }


}
