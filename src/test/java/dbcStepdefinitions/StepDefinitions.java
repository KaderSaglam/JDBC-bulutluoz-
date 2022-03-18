package dbcStepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StepDefinitions {
    String url="jdbc:sqlserver://184.168.194.58:1433;databaseName=hotelmycamp ; user=techproed;password=P2s@rt65";
    String username="techproed";
    String password="P2s@rt65";

    Connection connection;
    Statement statement;
    ResultSet resultSet;

    @Given("kullanici HMC veri tabanina baglanir")
    public void kullanici_hmc_veri_tabanina_baglanir() throws SQLException {
        // database'e baglanti kuruyoruz
        connection= DriverManager.getConnection(url,username,password);
        statement=connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

    }
    @Given("kullanici {string} tablosundaki {string} verilerini alir")
    public void kullanici_tablosundaki_verilerini_alir(String table, String field) throws SQLException {
        // Query calistiriyoruz
        // SELECT Price FROM tHOTELROOM
        String readQuery= "SELECT "+field+" FROM "+ table;
        resultSet=statement.executeQuery(readQuery);

    }
    @Given("kullanici {string} sutunundaki verileri okur")
    public void kullanici_sutunundaki_verileri_okur(String field) throws SQLException {

        // resultSet bizim suana kadar kullandigimiz Set yapisinda degildir
        // resultSet iterator ile calisir

        // resultSet'teki bilgileri okumak istiyorsaniz
        // once iterator'i istediginiz yere gondermelisiniz

        resultSet.first(); // bu komut iterator'i ilk elemente goturur, gitti ise true, gidemezse false doner

        // iterator istenen konuma gidince artik elementi yazdirabiliriz

        System.out.println(resultSet.getString(field));

        // ikinci oda fiyatini gormek istersek
        // iterator'i yollamamiz lazim

        resultSet.next();

        System.out.println(resultSet.getString(field));


        System.out.println(resultSet.next()); // true

        System.out.println(resultSet.getString(field)); // resultSet.next() nerede olursa olsun iterator'u bir sonraki elemente gecirir


        // tum price sutununu yazdirmak istesem
        System.out.println("===============================");
        // resultSet ile calisirken iterator konumunu kontrol etmek zorundayiz
        // eger 1. elemana donmeden listeyi yazdirmaya devam edersem
        // kaldigi yerden devam edip 4. element ve sonrasini yazdirir

        resultSet.absolute(0);
        while(resultSet.next()){
            System.out.println(resultSet.getString(field));
        }

        // price sutununda kac data oldugunu bulalim
        // while loop ile resultSet.next() false donunceye kadar gittik
        // dolayisiyla artik iterator sonda

        resultSet.last();
        System.out.println(resultSet.getRow());

        // suanda tum price bilgileri resultSet uzerinde
        // eger bu bilgilerle birden fazla test yapacaksak
        // bu bilgileri Java ortamina almakta fayda var
        // ornegin bir List<Double> olusturup
        // tum price verilerini bu listeye ekleyebiliriz
        // boylece bir java objesi olan LIst sayesinde
        // price degerleri uzerinde istedigimiz testleri yapabiliriz

        resultSet.absolute(0);
        List<Double> priceList=new ArrayList<>();

        while (resultSet.next()){
            priceList.add(resultSet.getDouble(field));
        }

        System.out.println(priceList);
    }
}
