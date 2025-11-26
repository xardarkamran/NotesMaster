package com.navigation.live.data.mappers

interface Mapper<E, D> {
    fun mapFromEntity(entity: E): D
    fun mapFromDomain(domain: D): E
    fun mapFromEntityList(entities: List<E>): List<D> {
        return entities.map {
            mapFromEntity(it)
        }
    }

    fun mapFromDomainList(domain: List<D>): List<E> {
        return domain.map {
            mapFromDomain(it)
        }
    }
}