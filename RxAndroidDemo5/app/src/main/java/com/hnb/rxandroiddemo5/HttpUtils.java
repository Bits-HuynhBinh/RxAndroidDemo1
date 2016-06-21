package com.hnb.rxandroiddemo5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Huynh Binh PC on 6/17/2016.
 */
public class HttpUtils
{
    public static final String EXCEPTION = "EXCEPTION:- ";

    public static String httpGet(String requestURL) throws Exception
    {
        HttpURLConnection urlConnection = null;


        URL url = new URL(requestURL);
        urlConnection = (HttpURLConnection) url.openConnection();

        InputStream inputStream = urlConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

        String result = "";
        String data = "";
        while ((data = bufferedReader.readLine()) != null)
        {
            result += data + "\n";
        }

        urlConnection.disconnect();

        return result;
    }


    public static String httpGet1(String requestURL)
    {
        HttpURLConnection urlConnection = null;
        String result = "";
        String data = "";

        try
        {
            URL url = new URL(requestURL);
            urlConnection = (HttpURLConnection) url.openConnection();

            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));

            while ((data = bufferedReader.readLine()) != null)
            {
                result += data + "\n";
            }
        }
        catch (Exception ex)
        {
            result = "";
            result = EXCEPTION + ex.getMessage();
        }
        finally
        {
            urlConnection.disconnect();
        }

        return result;
    }

    public static String httpPost(String requestURL, HashMap<String, String> postDataParams)
    {
        HttpURLConnection conn = null;
        URL url;
        String response = "";
        try
        {
            url = new URL(requestURL);

            conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK)
            {
                String line;
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                while ((line = br.readLine()) != null)
                {
                    response += line;
                }
            }
            else
            {
                response = EXCEPTION + responseCode;
            }
        }
        catch (Exception e)
        {
            response = EXCEPTION + e.getMessage();
        }
        finally
        {
            conn.disconnect();
        }

        return response;
    }


    private static String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException
    {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet())
        {
            if (first)
            {
                first = false;
            }
            else
            {
                result.append("&");
            }

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }

}
