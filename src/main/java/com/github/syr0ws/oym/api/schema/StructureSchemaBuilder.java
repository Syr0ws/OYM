package com.github.syr0ws.oym.api.schema;

public interface StructureSchemaBuilder {

    <T> StructureSchema<T> build(Class<T> type);
}
