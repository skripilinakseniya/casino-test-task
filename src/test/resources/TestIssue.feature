Feature: the user opens the login page,
  user enters login and password,
  user opens the admin page,
  the admin panel loads in page
  user opens players page
  user sorts players by registration date


  Scenario: Успешная авторизация
    Given User open auth page
    When User enters login "admin1"
    And User enters password "[9k<k8^z!+$$GkuP"
    And User submits request for auth
    And Assert loading admin menu
    Then Logout

  Scenario: Загрузка таблицы игроков
    Given User open auth page
    When User enters login "admin1"
    And User enters password "[9k<k8^z!+$$GkuP"
    And User submits request for auth
    And Submit request for open players menu
    And Assert loading players table
    Then Logout

  Scenario: Сортировка игроков по дате регистрации
    Given User open auth page
    When User enters login "admin1"
    And User enters password "[9k<k8^z!+$$GkuP"
    And User submits request for auth
    And Submit request for open players menu
    And Assert loading players table
    And Submit sort by date
    And Assert sorted
    Then Logout

