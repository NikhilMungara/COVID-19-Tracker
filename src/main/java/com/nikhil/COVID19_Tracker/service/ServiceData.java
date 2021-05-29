package com.nikhil.COVID19_Tracker.service;

import com.nikhil.COVID19_Tracker.model.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class ServiceData {

    private static String dataUrl = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";

     List<LocationStats> stats = new ArrayList();

    @PostConstruct
    @Scheduled(cron = "* * 0 * * *")
    public void fetchData() throws IOException, InterruptedException {

        List<LocationStats> newStats = new ArrayList();
        HttpClient client = HttpClient.newHttpClient();    //using HTTP client to make calls to the url containing the data
        HttpRequest request = HttpRequest.newBuilder(URI.create(dataUrl)).build();  //building request to client for data
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response.body());
        StringReader reader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);

        for (CSVRecord record : records) {

            LocationStats stat = new LocationStats();
            stat.setState(record.get("Province_State"));
            stat.setCountry(record.get("Country_Region"));
            stat.setTotalCases(Integer.parseInt(record.get(record.size()-1)));  //System.out.println(state);
            int prevCases = Integer.parseInt(record.get(record.size()-2));
            int latestCases = Integer.parseInt(record.get(record.size()-1));
            stat.setDifferenceInCasesEveryDay(latestCases-prevCases);
            System.out.println(stat);
            newStats.add(stat);
        }
        stats = newStats;

    }

   public List<LocationStats> getStats() {
       return stats;
   }
}
