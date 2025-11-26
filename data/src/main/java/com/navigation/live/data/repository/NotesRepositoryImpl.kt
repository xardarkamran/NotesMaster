package com.navigation.live.data.repository

import com.navigation.live.data.local.dao.NotesDao
import com.navigation.live.data.local.entity.NoteEntity
import com.navigation.live.data.mappers.Mapper
import com.navigation.live.domain.model.Note
import com.navigation.live.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class NotesRepositoryImpl (
    private val notesDao: NotesDao,
    private val noteMapper : Mapper<NoteEntity, Note>
) : NoteRepository {
    override suspend fun insertNote(note: Note) {
        notesDao.saveNote(noteMapper.mapFromDomain(note))
    }

    override suspend fun deleteNote(note: Note) {
        notesDao.deleteNote(noteMapper.mapFromDomain(note))
    }

    override suspend fun updateNote(note: Note) {
        notesDao.updateNote(noteMapper.mapFromDomain(note))
    }

    override fun getNotes(): Flow<List<Note>> {
        return notesDao.getAllNotes().map { notesList ->
            notesList.map {
                noteMapper.mapFromEntity(it)
            }

        }
    }

    override suspend fun getNoteById(id: Int): Note {
        return noteMapper.mapFromEntity(notesDao.getNoteById(id))
    }
}