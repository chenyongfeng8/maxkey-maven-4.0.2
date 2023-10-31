/*
 * Copyright [2021] [MaxKey of copyright http://www.maxkey.top]
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 

package org.dromara.maxkey.persistence.service;

import org.dromara.maxkey.entity.FileUpload;
import org.dromara.maxkey.persistence.mapper.FileUploadMapper;
import org.dromara.mybatis.jpa.JpaService;
import org.springframework.stereotype.Repository;

@Repository
public class FileUploadService  extends JpaService<FileUpload>{

	public FileUploadService() {
		super(FileUploadMapper.class);
	}

	/* (non-Javadoc)
	 * @see com.connsec.db.service.BaseService#getMapper()
	 */
	@Override
	public FileUploadMapper getMapper() {
		return (FileUploadMapper)super.getMapper();
	}
}
