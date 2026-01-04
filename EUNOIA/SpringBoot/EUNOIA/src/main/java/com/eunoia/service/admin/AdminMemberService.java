package com.eunoia.service.admin;

import java.util.List;

import com.eunoia.dto.admin.PendingMemberResponseDTO;

public interface AdminMemberService {
    List<PendingMemberResponseDTO> getPendingMembers();

    void approveMember(Long id);
}
