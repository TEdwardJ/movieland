package edu.luxoft.dbservice;

public class FieldMappingDescription {

    private boolean primaryKey;

    public boolean isPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(boolean primaryKey) {
        this.primaryKey = primaryKey;
    }

    private String dbName;
    private String objName;
    private Class fieldClass;

    public FieldMappingDescription(String dbName, String objName, Class fieldClass) {
        this.dbName = dbName;
        this.objName = objName;
        this.fieldClass = fieldClass;
    }

    public String getDbName() {
        return dbName;
    }

    public String getObjName() {
        return objName;
    }

    public Class getFieldClass() {
        return fieldClass;
    }
}
