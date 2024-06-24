### Script for exporting ADO Testplan 'xPlanBox' into markdown
from osgeo import ogr
from pathlib import Path
import sys

def gmlToWkt(gmlFile):
    gml = Path(gmlFile).read_text()
    geom = ogr.CreateGeometryFromGML(gml)
    wkb  = geom.ExportToWkt()
    print(wkb)

if __name__ == "__main__":
    if len(sys.argv) != 2:
        print("Verwendung: python transformGeom.py <GML>")
        sys.exit(1)

    gmlFile = sys.argv[1]
    gmlToWkt(gmlFile)