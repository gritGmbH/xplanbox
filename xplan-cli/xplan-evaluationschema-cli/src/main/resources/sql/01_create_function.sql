CREATE OR REPLACE FUNCTION lgv.clone_schema(source_schema text, dest_schema text) RETURNS void AS
$BODY$
DECLARE
  tableName text;
BEGIN
    EXECUTE 'CREATE SCHEMA ' || dest_schema ;

    FOR tableName IN
        SELECT TABLE_NAME::text FROM information_schema.TABLES WHERE table_schema = source_schema AND TABLE_NAME LIKE 'xplan_%'
    LOOP
        EXECUTE 'CREATE TABLE ' || dest_schema || '.' || tableName || ' (LIKE ' || source_schema || '.' || tableName || ' INCLUDING CONSTRAINTS INCLUDING INDEXES INCLUDING DEFAULTS)';
    END LOOP;

END;
$BODY$
LANGUAGE plpgsql VOLATILE