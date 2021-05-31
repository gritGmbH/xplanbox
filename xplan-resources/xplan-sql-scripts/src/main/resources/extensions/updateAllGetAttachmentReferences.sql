DO
$do$
  DECLARE
    u text = 'http://localhost:8083/xplan-wms';
    i record;
  BEGIN
  FOR i IN SELECT table_schema, table_name, column_name FROM information_schema.columns WHERE table_schema like 'xplansyn%' AND table_name  like 'xplan_%' AND (column_name = 'xplan_externereferenz' OR column_name LIKE 'xplan_raster%' OR column_name LIKE 'xplan_ref%')
  LOOP
    EXECUTE 
        'UPDATE ' || i.table_schema || '.' || i.table_name || 
        ' SET ' || i.column_name || ' = replace( ' || i.column_name || ', ''[/getAttachment?'', concat(''['', '''|| u || ''', ''/getAttachment?''))';
  END LOOP;
  END
$do$;
