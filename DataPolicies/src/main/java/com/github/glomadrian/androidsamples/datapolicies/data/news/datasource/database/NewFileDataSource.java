package com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.database;

import android.content.Context;
import com.github.glomadrian.androidsamples.datapolicies.domain.model.NewItem;
import com.github.glomadrian.androidsamples.datapolicies.data.news.datasource.database.exception.FileDataSourceException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Store a file of news in internal storage
 *
 * @author Adrián García Lomas
 */
public class NewFileDataSource implements DataBaseDataSource {

  private static final String FILENAME_EXTENSION = ".json";
  private Gson gson;
  private Context context;

  public NewFileDataSource(Context context) {
    this.context = context;
    init();
  }

  private void init() {
    gson = new Gson();
  }

  public void saveToStorage(List<NewItem> news) throws IOException {
    String newsJson = gson.toJson(news);
    File file = new File(context.getFilesDir(), generateTodayFileName());
    OutputStream outputStream = new FileOutputStream(file);
    outputStream.write(newsJson.getBytes());
    outputStream.close();
  }

  private List<NewItem> getFromStorage() throws IOException {
    File file = new File(context.getFilesDir(), generateTodayFileName());
    StringBuilder stringBuilder = new StringBuilder();
    BufferedReader br = new BufferedReader(new FileReader(file));
    String line;

    while ((line = br.readLine()) != null) {
      stringBuilder.append(line);
      stringBuilder.append('\n');
    }

    br.close();
    Type newsType = new TypeToken<ArrayList<NewItem>>() {
    }.getType();
    return gson.fromJson(stringBuilder.toString(), newsType);
  }

  private String generateTodayFileName() {
    Calendar calendar = Calendar.getInstance();
    int day = calendar.get(Calendar.DAY_OF_MONTH);
    int month = calendar.get(Calendar.DAY_OF_MONTH);
    int year = calendar.get(Calendar.YEAR);
    return "" + year + month + day + FILENAME_EXTENSION;
  }

  @Override
  public List<NewItem> getToadyNews() {
    try {
      return getFromStorage();
    } catch (IOException e) {
      FileDataSourceException fileDataSourceException = new FileDataSourceException();
      fileDataSourceException.setStackTrace(e.getStackTrace());
      throw fileDataSourceException;
    }
  }

  @Override
  public void saveNews(List<NewItem> news) {
    try {
      saveToStorage(news);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  @Override
  public void clear() {
    File file = new File(context.getFilesDir(), generateTodayFileName());
    file.delete();
  }
}
