abstract class ReportGenerator {


    public final void generateReport() {
        fetchData();
        formatData();
        if (isHeaderRequired()) {
            addHeader();
        }
        generateFile();
        saveFile();
    }


    protected void fetchData() {
        System.out.println("Получение данных для отчета...");
    }


    protected boolean isHeaderRequired() {
        return true;
    }


    protected abstract void formatData();
    protected abstract void addHeader();
    protected abstract void generateFile();
    protected abstract void saveFile();
}


class PdfReport extends ReportGenerator {

    @Override
    protected void formatData() {
        System.out.println("Форматирование данных для PDF-отчета...");
    }

    @Override
    protected void addHeader() {
        System.out.println("Добавление заголовка для PDF-отчета...");
    }

    @Override
    protected void generateFile() {
        System.out.println("Генерация PDF-файла...");
    }

    @Override
    protected void saveFile() {
        System.out.println("Сохранение PDF-отчета...");
    }
}

class ExcelReport extends ReportGenerator {

    @Override
    protected void formatData() {
        System.out.println("Форматирование данных для Excel-отчета...");
    }

    @Override
    protected void addHeader() {
        System.out.println("Добавление заголовка для Excel-отчета...");
    }

    @Override
    protected void generateFile() {
        System.out.println("Генерация Excel-файла...");
    }

    @Override
    protected void saveFile() {
        System.out.println("Сохранение Excel-отчета...");
    }
}

class HtmlReport extends ReportGenerator {

    @Override
    protected void formatData() {
        System.out.println("Форматирование данных для HTML-отчета...");
    }

    @Override
    protected void addHeader() {
        System.out.println("Добавление заголовка для HTML-отчета...");
    }

    @Override
    protected void generateFile() {
        System.out.println("Генерация HTML-файла...");
    }

    @Override
    protected void saveFile() {
        System.out.println("Сохранение HTML-отчета...");
    }


    @Override
    protected boolean isHeaderRequired() {
        return false;
    }
}






public class Template_Method {
    public static void main(String[] args) {
        ReportGenerator pdfReport = new PdfReport();
        ReportGenerator excelReport = new ExcelReport();
        ReportGenerator htmlReport = new HtmlReport();

        System.out.println("Создание PDF-отчета:");
        pdfReport.generateReport();

        System.out.println("\nСоздание Excel-отчета:");
        excelReport.generateReport();

        System.out.println("\nСоздание HTML-отчета:");
        htmlReport.generateReport();
    }

}


