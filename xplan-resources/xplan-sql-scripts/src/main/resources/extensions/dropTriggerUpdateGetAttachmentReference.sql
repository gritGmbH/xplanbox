DROP function IF EXISTS updateGetAttachmentUrl;

DO
$do$
  DECLARE
    i record;
  BEGIN
  FOR i IN SELECT event_object_schema as table_schema, event_object_table as table_name, trigger_name FROM information_schema.triggers WHERE event_object_schema like 'xplansyn%' AND event_object_table  like 'xplan_%'
  LOOP
    IF( i.trigger_name LIKE substring(concat('i_', i.table_schema, '_', substring(i.table_name from 7)) from 0 for 63) || '_%'
       OR i.trigger_name LIKE substring(concat('u_', i.table_schema, '_', substring(i.table_name from 7)) from 0 for 63) || '_%') THEN
      EXECUTE
        'DROP TRIGGER IF EXISTS ' || i.trigger_name || ' ON ' || i.table_schema || '.' || i.table_name || ';';
    END IF;
  END LOOP;
  END
$do$;

DO
$do$
  DECLARE
    i record;
  BEGIN
  FOR i IN select p.proname as function_name from pg_proc p left join pg_namespace n on p.pronamespace = n.oid left join pg_language l on p.prolang = l.oid left join pg_type t on t.oid = p.prorettype where n.nspname not in ('pg_catalog', 'information_schema') AND p.proname LIKE 'updategetattachmenturl%'
  LOOP
    EXECUTE
      'DROP FUNCTION IF EXISTS ' || i.function_name || ';';
  END LOOP;
  END
$do$;