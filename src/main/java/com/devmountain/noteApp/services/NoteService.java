package com.devmountain.noteApp.services;

import com.devmountain.noteApp.dtos.NoteDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface NoteService {
    //For adding a note
    @Transactional
    void addNote(NoteDto noteDto, Long userId);

    @Transactional
    void deleteNoteById(Long noteId);

    @Transactional
    void updateNoteById(NoteDto noteDto);

    //public List<NoteDto> getAllNotesByUserId (Long userId){
    //        Optional<User> userOptional = userRepository.findById(userId);
    //        if(userOptional.isPresent()){
    //            List<Note> noteList = noteRepository.findAll(userId);
    //            return noteList.stream().map(note -> new NoteDto(note)).collect(Collectors.toList());
    //        }
    //        return Collections.emptyList();
    //}
    Optional<NoteDto> getNoteById(Long noteId);
}
