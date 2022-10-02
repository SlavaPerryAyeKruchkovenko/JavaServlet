package app;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@WebServlet(urlPatterns = "/")
public class MyServlet extends HttpServlet {
    //private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
    @Override
    public void init(ServletConfig var1) throws ServletException{
        super.init(var1);
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String path = req.getParameter("path");
        if (path == null) {
            path = "C:\\";
        }
        File file = new File(path);
        if (!file.exists()) {
            file.mkdir();
        }
        if (file.isDirectory()) {
            showFiles(req, file);

            req.setAttribute("date", new SimpleDateFormat("dd.MM.yyyy HH:mm:ss").format(new Date()));
            req.setAttribute("path", path);

            RequestDispatcher requestDispatcher = req.getRequestDispatcher("explore.jsp");
            requestDispatcher.forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

    }

    private void downloadFile(HttpServletResponse resp, File file) throws IOException {
        resp.setContentType("text/plain");
        resp.setHeader("Content-disposition", "attachment; filename=" + file.getName());

        try (InputStream in = Files.newInputStream(file.toPath()); OutputStream out = resp.getOutputStream()) {
            byte[] buffer = new byte[1048];

            int numBytesRead;
            while ((numBytesRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, numBytesRead);
            }
        }
    }

    private void showFiles(HttpServletRequest req, File file) {
        File[] files = file.listFiles();
        if (files != null) {
            req.setAttribute("files", getFiles(files));
            req.setAttribute("directories", getDirectories(files));
        }
    }
    private List<File> getFiles(File[] files){
        return Arrays.stream(files).filter(File::isFile).collect(Collectors.toList());
    }
    private List<File> getDirectories(File[] files){
        return Arrays.stream(files).filter(File::isDirectory).collect(Collectors.toList());
    }
}