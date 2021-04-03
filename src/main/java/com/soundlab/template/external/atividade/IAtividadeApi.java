package com.soundlab.template.external.atividade;

import com.soundlab.template.external.atividade.models.AtividadeResponseDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IAtividadeApi {
    @GET("/atividades")
    Call<List<AtividadeResponseDTO>> getAtividades();

    @GET("/atividades/{id}")
    Call<AtividadeResponseDTO> getAtividadeById(@Path("id") Long id);

    @POST("/atividades")
    Call<Void> saveGrade(@Query("idAtividade") Long activityId,
                                         @Query("idUser") Long userId,
                                         @Query("grade") Integer grade);
}
