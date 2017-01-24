package com.refactor.case8.util;

import com.cmb.ccd.common.domain.model.DomainValueObject;
import com.cmb.ccd.common.util.IdGenerator;

import javax.persistence.Column;
import java.io.Serializable;

public class DataSourceId extends DomainValueObject implements Comparable<DataSourceId>, Serializable {
    public static DataSourceId of(String id) {
        if (id == null || id.length() == 0)
            return null;
        return new DataSourceId(id);
    }

    @Column(name = "DATASOURCE_ID")
    private String id;

    DataSourceId() {
        this.id = IdGenerator.id();
    }

    DataSourceId(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }

    @Override
    public int compareTo(DataSourceId o) {
        return id.compareTo(o.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataSourceId that = (DataSourceId) o;
        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public String toString() {
        return "datasourceId{" +
                "id='" + id + '\'' +
                '}';
    }
}