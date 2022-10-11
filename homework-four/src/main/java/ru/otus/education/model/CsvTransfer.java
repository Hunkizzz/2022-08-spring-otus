package ru.otus.education.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
public class CsvTransfer {

    private List<String[]> csvStringList;

    private List<CsvBean> csvList;

    public List<String[]> getCsvStringList() {
        if (csvStringList != null) return csvStringList;
        return new ArrayList<>();
    }

    public void setCsvStringList(List<String[]> csvStringList) {
        this.csvStringList = csvStringList;
    }

    public void addLine(String[] line) {
        if (this.csvList == null) this.csvStringList = new ArrayList<>();
        this.csvStringList.add(line);
    }

    public List<CsvBean> getCsvList() {
        if (csvList != null) return csvList;
        return new ArrayList<>();
    }

    public void setCsvList(List<CsvBean> csvList) {
        this.csvList = csvList;
    }
}