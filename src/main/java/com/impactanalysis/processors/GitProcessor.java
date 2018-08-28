package com.impactanalysis.processors;

import org.springframework.stereotype.Component;

import com.impactanalysis.dto.GitResponseDTO;

@Component
public class GitProcessor {

	public GitResponseDTO processResponse(GitResponseDTO gitResponseDTO) {
		if(gitResponseDTO!=null) {
			gitResponseDTO.setTotalFiles(gitResponseDTO.getFiles()!=null ? gitResponseDTO.getFiles().size() : null);
			gitResponseDTO.setTotalItems(gitResponseDTO.getItems()!=null ? gitResponseDTO.getItems().size() : null);
		}
		return gitResponseDTO;
	}
}
