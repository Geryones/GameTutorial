package models;

import textures.ModelTexture;

/**
 * Created by mai714 on 02.09.2015.
 */
public class TexturedModel {

    private RawModel rawModel;
    private ModelTexture modelTexture;

    public TexturedModel(RawModel model, ModelTexture texture){
        this.rawModel=model;
        this.modelTexture=texture;
    }

    public RawModel getRawModel() {
        return rawModel;
    }

    public ModelTexture getModelTexture() {
        return modelTexture;
    }
}
