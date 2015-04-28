package test

import grails.compiler.GrailsCompileStatic
import grails.gorm.PagedResultList
import grails.transaction.Transactional
import org.grails.datastore.mapping.query.api.BuildableCriteria
import defpackage.exceptions.ResourceNotFound

//@GrailsCompileStatic
@Transactional(readOnly = true)
class ClassifierSearchService {

	Classifier queryForClassifier(Long classifierId) {
		if (!classifierId || classifierId < 0) {
			throw new IllegalArgumentException('no.valid.id')
		}
		Classifier classifier = Classifier.where { id == classifierId }.find()
		if (!classifier) {
			throw new ResourceNotFound("No Classifier found with Id :[$classifierId]")
		}
		return classifier
	}

	PagedResultList search(ClassifierSearchCommand cmd, Map pagingParams) {

		BuildableCriteria criteriaBuilder = (BuildableCriteria) Classifier.createCriteria()
		PagedResultList results = (PagedResultList) criteriaBuilder.list(
				offset: pagingParams.offset,
				max: pagingParams.max,
				order: pagingParams.order,
				sort: pagingParams.sort
		) {
			searchCriteria criteriaBuilder, cmd
		}
		return results
	}

	private void searchCriteria(BuildableCriteria builder, ClassifierSearchCommand cmd) {
		String searchString = cmd.searchString

		builder.with {
			//readOnly true
			if (cmd.id) {
				eq('id', cmd.id)
			}
			if (searchString) {
				or {
					eq('id', -1L)

					if (searchString.isLong()) {
						eq('id', searchString.toLong())
					}
					like('propertyname', searchString + '%')
					like('description', searchString + '%')
					like('constant', searchString + '%')
				}
			}
			if (cmd.classname){
				ilike('classname', cmd.classname + '%')
			}
			if (cmd.constant){
				ilike('constant', cmd.constant + '%')
			}
			if (cmd.description){
				ilike('description', cmd.description + '%')
			}
			if (cmd.propertyname){
				ilike('propertyname', cmd.propertyname + '%')
			}
		}
	}
}
