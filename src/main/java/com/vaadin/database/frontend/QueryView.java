package com.vaadin.database.frontend;

import com.vaadin.database.data.entity.Connection_requests;
import com.vaadin.database.frontend.queries.*;
import com.vaadin.database.frontend.views.*;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.contextmenu.MenuItem;
import com.vaadin.flow.component.menubar.MenuBar;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLink;

import java.awt.*;

@Route("")
@PageTitle("queries")
public class QueryView extends AppLayout {
    private MenuBar menuBar = new MenuBar();

    public QueryView(){
        MenuItem q = menuBar.addItem("Запросы");

        MenuItem t = menuBar.addItem("Таблицы");

        t.getSubMenu().addItem(new RouterLink("Адреса", AddressView.class));
        t.getSubMenu().addItem(new RouterLink("Балансы", BalancesView.class));
        t.getSubMenu().addItem(new RouterLink("Абоненты", CallersView.class));
        t.getSubMenu().addItem(new RouterLink("Клиенты", ClientsView.class));
        t.getSubMenu().addItem(new RouterLink("Цены на подключение", Connection_pricesView.class));
        t.getSubMenu().addItem(new RouterLink("Запросы на подключение", Connection_requestsView.class));
        t.getSubMenu().addItem(new RouterLink("Междугородние звонки", Long_distance_callsView.class));
        t.getSubMenu().addItem(new RouterLink("Цены на межгород", Long_distance_call_pricesView.class));
        t.getSubMenu().addItem(new RouterLink("Возможности установки телефонов", Installing_possibilitiesView.class));
        t.getSubMenu().addItem(new RouterLink("Номера телефонов", Phone_numbersView.class));
        t.getSubMenu().addItem(new RouterLink("Типы телефонов", Phone_typesView.class));
        t.getSubMenu().addItem(new RouterLink("Телефоны", PhonesView.class));
        t.getSubMenu().addItem(new RouterLink("Общественные телефоны", Public_phonesView.class));
        t.getSubMenu().addItem(new RouterLink("Абонентская плата", Subscription_feeView.class));
        t.getSubMenu().addItem(new RouterLink("АТС", Telephone_exchangesView.class));



        q.getSubMenu().addItem(new RouterLink("Получить перечень абонентов по фамилии, возрасту и атс", First.class));
        q.getSubMenu().addItem(new RouterLink("Получить перечень свободных телефонных номеров по АТС и адресу", Second.class));
        q.getSubMenu().addItem(new RouterLink("Получить перечень должников по сумме или сроку долга", Third.class));
        q.getSubMenu().addItem(new RouterLink("Получить АТС с должниками или с максимальной задолженностью", Fourth.class));
        q.getSubMenu().addItem(new RouterLink("Получить перечень общественных телефонов по району и АТС", Fifth.class));
        q.getSubMenu().addItem(new RouterLink("Получить процентное соотношение обычных и льготных абонентов", Sixth.class));
        q.getSubMenu().addItem(new RouterLink("Получить перечень абонентов, имеющих параллельные телефоны", Seventh.class));
        q.getSubMenu().addItem(new RouterLink("Получить перечень телефонов по улицам", Eighth.class));
        q.getSubMenu().addItem(new RouterLink("Получить город с максимальным числом разговоров", Ninth.class));
        q.getSubMenu().addItem(new RouterLink("Получить полную информацию об абонентах с заданным телефонным номером", Tenth.class));
        q.getSubMenu().addItem(new RouterLink("Получить перечень спаренных телефонов, для которых есть техническая возможность заменить их на обычные", Eleventh.class));
        q.getSubMenu().addItem(new RouterLink("Получить перечень телефонов, с которых за некоторый период времени было произведено менее определенного числа звонков", Twelfth.class));
        q.getSubMenu().addItem(new RouterLink("Получить перечень должников для уведомления или отключения", Thirteenth.class));
      addToNavbar(menuBar);
    }


}
