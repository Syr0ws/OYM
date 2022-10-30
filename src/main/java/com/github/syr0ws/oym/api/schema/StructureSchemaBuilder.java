package com.github.syr0ws.oym.api.schema;

/**
 * Analyses the content of a class and represents it as an internal structure
 * manipulable by the program.
 */
public interface StructureSchemaBuilder {

    /**
     * Build a schema for a specific object type. A schema contains metadata about an object
     * type which will be used to get fields and assign their values during the mapping process.
     * All these fields must be annotated with the @Key annotation.
     * @param type a type of object to build the schema of.
     * @return the schema of the object.
     */
    <T> StructureSchema<T> build(Class<T> type);
}
