package study.ian.redso.model

import study.ian.redso.contractor.ContentFragmentContractor
import study.ian.redso.service.ServiceBuilder

class ContentFragmentModel : ContentFragmentContractor.Model {

    private val redSoService = ServiceBuilder.create()

    override fun getCatalog(team: String, page: Int) = redSoService.getCatalog(team, page)
}