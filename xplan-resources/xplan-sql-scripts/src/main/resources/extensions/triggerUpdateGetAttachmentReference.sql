CREATE OR REPLACE FUNCTION updateGetAttachmentUrl()
  RETURNS TRIGGER AS $$
    DECLARE
      u text = 'http://localhost:8083/xplan-wms';
    BEGIN
        if (NEW.xplan_externereferenz) THEN
          NEW.xplan_externereferenz := replace( NEW.xplan_externereferenz, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_rasteraenderung) THEN
          NEW.xplan_rasteraenderung := replace( NEW.xplan_rasteraenderung, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_rasterbasis) THEN
          NEW.xplan_rasterbasis := replace( NEW.xplan_rasterbasis, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW. xplan_refbegruendung) THEN
          NEW. xplan_refbegruendung := replace( NEW. xplan_refbegruendung, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_refbegruendunginhalt) THEN
          NEW.xplan_refbegruendunginhalt := replace( NEW.xplan_refbegruendunginhalt, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_refbeschluss) THEN
          NEW.xplan_refbeschluss := replace( NEW.xplan_refbeschluss, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_refbeschreibung) THEN
          NEW.xplan_refbeschreibung := replace( NEW.xplan_refbeschreibung, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_referenzmimetype) THEN
          NEW.xplan_referenzmimetype := replace( NEW.xplan_referenzmimetype, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_referenzmimetypecode) THEN
          NEW.xplan_referenzmimetypecode := replace( NEW.xplan_referenzmimetypecode, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_referenzname) THEN
          NEW.xplan_referenzname := replace( NEW.xplan_referenzname, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_referlaeuterung) THEN
          NEW.xplan_referlaeuterung := replace( NEW.xplan_referlaeuterung, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_refexternalcodelist) THEN
          NEW.xplan_refexternalcodelist := replace( NEW.xplan_refexternalcodelist, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_refgebaedequerschnitt) THEN
          NEW.xplan_refgebaedequerschnitt := replace( NEW.xplan_refgebaedequerschnitt, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_refgruenordnungsplan) THEN
          NEW.xplan_refgruenordnungsplan := replace( NEW.xplan_refgruenordnungsplan, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_refgrundstuecksverzeichnis) THEN
          NEW.xplan_refgrundstuecksverzeichnis := replace( NEW.xplan_refgrundstuecksverzeichnis, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_refkoordinatenliste) THEN
          NEW.xplan_refkoordinatenliste := replace( NEW.xplan_refkoordinatenliste, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_reflandschaftsplan) THEN
          NEW.xplan_reflandschaftsplan := replace( NEW.xplan_reflandschaftsplan, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_reflegende) THEN
          NEW.xplan_reflegende := replace( NEW.xplan_reflegende, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_reflegendenbild) THEN
          NEW.xplan_reflegendenbild := replace( NEW.xplan_reflegendenbild, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_refmassnahmentext) THEN
          NEW.xplan_refmassnahmentext := replace( NEW.xplan_refmassnahmentext, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_refpflanzliste) THEN
          NEW.xplan_refpflanzliste := replace( NEW.xplan_refpflanzliste, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_refplangrundlage) THEN
          NEW.xplan_refplangrundlage := replace( NEW.xplan_refplangrundlage, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_refrechtsplan) THEN
          NEW.xplan_refrechtsplan := replace( NEW.xplan_refrechtsplan, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_refsatzung) THEN
          NEW.xplan_refsatzung := replace( NEW.xplan_refsatzung, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_refscan) THEN
          NEW.xplan_refscan := replace( NEW.xplan_refscan, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_reftext) THEN
          NEW.xplan_reftext := replace( NEW.xplan_reftext, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_reftextabschnitte) THEN
          NEW.xplan_reftextabschnitte := replace( NEW.xplan_reftextabschnitte, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_reftextinhalt) THEN
          NEW.xplan_reftextinhalt := replace( NEW.xplan_reftextinhalt, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        if (NEW.xplan_refumweltbericht) THEN
          NEW.xplan_refumweltbericht := replace( NEW.xplan_refumweltbericht, '[/getAttachment?', concat('[', u , '/getAttachment?'));
        END IF;
        RETURN NEW;
    END;
  $$ LANGUAGE 'plpgsql';

DO
$do$
  DECLARE
    i record;
  BEGIN
  FOR i IN SELECT table_schema, table_name, column_name FROM information_schema.columns WHERE table_schema like 'xplansyn%' AND table_name  like 'xplan_%' AND (column_name = 'xplan_externereferenz' OR column_name LIKE 'xplan_raster%' OR column_name LIKE 'xplan_ref%')
  LOOP
    EXECUTE 
        'CREATE TRIGGER i_' || i.table_schema || '_' || substring(i.table_name from 7) || '_' || substring(i.column_name from 7) || ''
        ' BEFORE INSERT ON ' || i.table_schema || '.' || i.table_name || ' FOR EACH ROW EXECUTE PROCEDURE updateGetAttachmentUrl();';
  END LOOP;
  END
$do$;


DO
$do$
  DECLARE
    i record;
  BEGIN
  FOR i IN SELECT table_schema, table_name, column_name FROM information_schema.columns WHERE table_schema like 'xplansyn%' AND table_name  like 'xplan_%' AND (column_name = 'xplan_externereferenz' OR column_name LIKE 'xplan_raster%' OR column_name LIKE 'xplan_ref%')
  LOOP
    EXECUTE 
        'CREATE TRIGGER u_' || i.table_schema || '_' || substring(i.table_name from 7) || '_' || substring(i.column_name from 7) || ''
        ' BEFORE UPDATE ON ' || i.table_schema || '.' || i.table_name || ' FOR EACH ROW EXECUTE PROCEDURE updateGetAttachmentUrl();';
  END LOOP;
  END
$do$;
