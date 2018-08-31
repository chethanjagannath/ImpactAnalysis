package com.impactanalysis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.impactanalysis.clients.GitClient;
import com.impactanalysis.dto.GitRequestDTO;
import com.impactanalysis.dto.GitResponseDTO;
import com.impactanalysis.processors.GitProcessor;

@Service
public class GitService{

	@Autowired
	private GitClient gitClient;
	
	@Autowired
	private GitProcessor gitProcessor;

	public GitResponseDTO getCommitDetailsByDate(GitRequestDTO gitUserDTO) {
		GitResponseDTO gitResponseDTO = gitClient.getCommitDetailsByDate(gitUserDTO);
		return gitProcessor.processResponse(gitResponseDTO);
	}
	
	public GitResponseDTO getCommitDetailsByCommitId(GitRequestDTO gitUserDTO) {
		GitResponseDTO gitResponseDTO = gitClient.getCommitDetailsByCommitId(gitUserDTO);
		return gitProcessor.processResponse(gitResponseDTO);
	}
	
	public GitResponseDTO getCommitDetailsBetweenCommitIds(GitRequestDTO gitUserDTO) {
		GitResponseDTO gitResponseDTO = gitClient.getCommitDetailsBetweenCommitIds(gitUserDTO);
		return gitProcessor.processResponse(gitResponseDTO);
	}
}