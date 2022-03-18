Feature: US1001 Kullanici Database'e baglanip bilgileri okuyabilir
@db
  Scenario: TC01 kullanici database baglanip istedigi bilgileri okuyabilmeli

    Given kullanici HMC veri tabanina baglanir
    And kullanici "tHOTELROOM" tablosundaki "Price" verilerini alir
    And kullanici "Price" sutunundaki verileri okur