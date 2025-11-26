package com.navigation.live.data.mappers

import com.navigation.live.data.local.entity.NoteEntity
import com.navigation.live.domain.model.Note

class NoteMapperImpl : Mapper<NoteEntity, Note> {
    override fun mapFromEntity(entity: NoteEntity): Note {
        return Note(
            id = entity.id,
            title = entity.title,
            content = entity.content,
            timestamp = entity.timestamp,
            color = entity.color
        )
    }

    override fun mapFromDomain(domain: Note): NoteEntity {
        return NoteEntity(
            id = domain.id,
            title = domain.title,
            content = domain.content,
            timestamp = domain.timestamp,
            color = domain.color
        )
    }
}