# Необходимое ПО для запуска тестов:
* Java 14
* IntelliJIdea
* Скачать chromedriver.exe, соответствующий вашей операционной системе и версии ChromeBrowser'а

**После установки необходимого ПО выкачать проект**
**и открыть его в IntelliJIdea**

# API
* Перейти в директорию src/test/java/api/
* Открыть CasinoTest
* Запустить тест, нажав на Run Test (зеленый круглешок) слева от названия класса
* Результат будет виден на вкладке Run (снизу)

# UI
* Указать полный путь до chromedriver.exe в файле WebDriverCreator, который находится по пути проекта src/test/java/ui/
* В поле "private static final String PATH = <здесь будет полный путь до chromedriver.exe>"
* Пример: private static final String PATH = "C:\\Users\\kisen\\chromedriver.exe";
* Перейти в директорию src/test/resources
* Открыть TestIssue.feature
* Запустить тест, нажав на Run Test (зеленый треугольник) слева от названия Feature
* Результат будет виден на вкладке Run (снизу)