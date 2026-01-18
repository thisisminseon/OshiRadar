package controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import model.CafeDto;

@WebServlet("/nearcafe/*")
public class NearCafeController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Kakao REST API Key (hard-coded as requested)
    private static final String KAKAO_REST_API_KEY = "8400dc4911d48726c1280fad1fd34d73";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doAction(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doAction(request, response);
    }

    protected void doAction(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String uri = request.getPathInfo();
        if (uri == null || "/".equals(uri)) {
            uri = "/list.do";
        }

        if (uri.equals("/list.do")) {
            list(request, response);
        }
    }

    // List page (Kakao API, within 1km)
    private void list(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // User location (Daejeon)
        double userLat = 36.326667;
        double userLng = 127.408056;

        // Kakao category search (Cafe: CE7)
        String apiUrl =
            "https://dapi.kakao.com/v2/local/search/category.json" +
            "?category_group_code=CE7" +
            "&x=" + userLng +
            "&y=" + userLat +
            "&radius=1000" +
            "&size=15";

        HttpURLConnection conn = (HttpURLConnection) new URL(apiUrl).openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Authorization", "KakaoAK " + KAKAO_REST_API_KEY);
        conn.setRequestProperty("Content-Type", "application/json");

        BufferedReader br = new BufferedReader(
            new InputStreamReader(conn.getInputStream(), "UTF-8")
        );

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }

        JSONObject json = new JSONObject(sb.toString());
        JSONArray documents = json.getJSONArray("documents");

        List<CafeDto> resultList = new ArrayList<>();

        for (int i = 0; i < documents.length(); i++) {
            JSONObject obj = documents.getJSONObject(i);

            CafeDto cafe = new CafeDto();
            cafe.setName(obj.getString("place_name"));
            cafe.setLatitude(obj.getDouble("y"));
            cafe.setLongitude(obj.getDouble("x"));
            cafe.setDescription(obj.optString("road_address_name", ""));
            cafe.setImage("default.jpg"); // fallback image

            double distance = getDistance(
                userLat,
                userLng,
                cafe.getLatitude(),
                cafe.getLongitude()
            );

            if (distance <= 1.0) {
                cafe.setDistance(distance);
                resultList.add(cafe);
            }
        }

        resultList.sort(Comparator.comparingDouble(CafeDto::getDistance));

        request.setAttribute("list", resultList);
        request.setAttribute("totalCount", resultList.size());

        request.getRequestDispatcher("/service/nearcafe.jsp")
               .forward(request, response);
    }

    // Distance calculation (km)
    private double getDistance(double lat1, double lon1, double lat2, double lon2) {

        double R = 6371.0;
        double dLat = Math.toRadians(lat2 - lat1);
        double dLon = Math.toRadians(lon2 - lon1);

        double a =
            Math.sin(dLat / 2) * Math.sin(dLat / 2) +
            Math.cos(Math.toRadians(lat1)) *
            Math.cos(Math.toRadians(lat2)) *
            Math.sin(dLon / 2) * Math.sin(dLon / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return Math.round(R * c * 10) / 10.0;
    }
}