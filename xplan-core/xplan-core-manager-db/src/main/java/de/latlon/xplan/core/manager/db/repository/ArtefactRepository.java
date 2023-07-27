/*-
 * #%L
 * xplan-manager-core - XPlan Manager Core Komponente
 * %%
 * Copyright (C) 2008 - 2023 Freie und Hansestadt Hamburg, developed by lat/lon gesellschaft für raumbezogene Informationssysteme mbH
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */
package de.latlon.xplan.core.manager.db.repository;

import de.latlon.xplan.core.manager.db.model.Artefact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.QueryHint;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import static org.hibernate.annotations.QueryHints.READ_ONLY;
import static org.hibernate.jpa.QueryHints.HINT_CACHEABLE;
import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

/**
 * @author <a href="mailto:goltz@lat-lon.de">Lyn Goltz </a>
 * @since 7.0
 */
@Repository
@Transactional
public interface ArtefactRepository extends org.springframework.data.repository.Repository<Artefact, Integer> {

	@Query(value = "from Artefact as a where a.id.plan.id = :plan and a.id.filename = :filename")
	Optional<Artefact> findByPlanAndFilename(@Param("plan") Integer plan, @Param("filename") String filename);

	@Query(value = "from Artefact as a where a.artefacttype = 'XPLANGML' and a.id.plan.id = :plan")
	Optional<Artefact> findXPlanGmlByPlan(@Param("plan") Integer plan);

	@QueryHints(value = { @QueryHint(name = HINT_FETCH_SIZE, value = "1"),
			@QueryHint(name = HINT_CACHEABLE, value = "false"), @QueryHint(name = READ_ONLY, value = "true") })
	@Query("select a from Artefact as a where a.id.plan.id = :plan")
	Stream<Artefact> findAllByPlanId(@Param("plan") Integer plan);

	@Query("select a.id.filename from Artefact as a where a.id.plan.id = :plan")
	List<String> findAllFileNamesByPlanId(@Param("plan") Integer plan);

}