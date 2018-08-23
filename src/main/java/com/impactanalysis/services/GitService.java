package com.impactanalysis.services;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.impactanalysis.clients.GitClient;
import com.impactanalysis.dto.GitRequestDTO;
import com.impactanalysis.dto.GitResponseDTO;

@Service
public class GitService{

	@Autowired
	private GitClient gitClient;

	public GitResponseDTO getCommitDetailsByDate(GitRequestDTO gitUserDTO) {
		GitResponseDTO gitResponseDTO = gitClient.getCommitDetailsByDate(gitUserDTO);
		return gitResponseDTO;
	}
	
	public GitResponseDTO getCommitDetailsByCommitId(String commitId) {
		GitResponseDTO gitResponseDTO = gitClient.getCommitDetailsByCommitId(commitId);
		return gitResponseDTO;
	}
	
	public GitResponseDTO getCommitDetailsBetweenCommitIds(String commitId1, String commitId2) {
		GitResponseDTO gitResponseDTO = gitClient.getCommitDetailsBetweenCommitIds(commitId1, commitId2);
		return gitResponseDTO;
	}
}
