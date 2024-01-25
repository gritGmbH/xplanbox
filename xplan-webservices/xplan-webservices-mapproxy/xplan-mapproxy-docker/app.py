import os
from logging.config import fileConfig
fileConfig(r'/srv/mapproxy/log.ini', defaults={'rootLoglevel': os.environ['XPLAN_MAPPROXY_ROOT_LOGLEVEL'], 'sourceLoglevel': os.environ['XPLAN_MAPPROXY_SOURCES_LOGLEVEL']})

from mapproxy.wsgiapp import make_wsgi_app
application = make_wsgi_app(r'/srv/mapproxy/mapproxy.yaml')