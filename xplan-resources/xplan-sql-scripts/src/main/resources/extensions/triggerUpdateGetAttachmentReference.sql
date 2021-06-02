DO
$do$
  DECLARE
    u text = 'http://localhost:8083/xplan-wms';
    i record;
  BEGIN
  FOR i IN SELECT table_schema, table_name, column_name FROM information_schema.columns WHERE table_schema like 'xplansyn%' AND table_name  like 'xplan_%' AND (column_name = 'xplan_externereferenz' OR column_name LIKE 'xplan_raster%' OR column_name LIKE 'xplan_ref%')
  LOOP
   EXECUTE
        'CREATE OR REPLACE FUNCTION updateGetAttachmentUrl_' || i.column_name || '()
           RETURNS TRIGGER AS $$
           BEGIN
            If (NEW.' || i.column_name || ' is not null) THEN
              NEW.' || i.column_name || ' := replace( NEW.' || i.column_name || ', ''[/getAttachment?'', concat(''['',''' || u || ''',''/getAttachment?''));
            END IF;
        RETURN NEW;
    END;
  $$ LANGUAGE ''plpgsql'';';
    EXECUTE 
        'CREATE TRIGGER i_' || i.table_schema || '_' || substring(i.table_name from 7) || '_' || substring(i.column_name from 7) || ''
        ' BEFORE INSERT ON ' || i.table_schema || '.' || i.table_name || ' FOR EACH ROW EXECUTE PROCEDURE updateGetAttachmentUrl_' || i.column_name || '();';
    EXECUTE
        'CREATE TRIGGER u_' || i.table_schema || '_' || substring(i.table_name from 7) || '_' || substring(i.column_name from 7) || ''
        ' BEFORE UPDATE ON ' || i.table_schema || '.' || i.table_name || ' FOR EACH ROW EXECUTE PROCEDURE updateGetAttachmentUrl_' || i.column_name || '();';
  END LOOP;
  END
$do$;